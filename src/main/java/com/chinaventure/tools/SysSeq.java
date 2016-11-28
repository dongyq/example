package com.chinaventure.tools;

import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by huaxiujun on 2015/11/24.
 * 获取系统seq id，分布式全局唯一，参考了mongodb 的object id及实现
 */
public class SysSeq {
    //原子id
    private static final AtomicInteger AI;
    //机器id
    private static final int machine;

    //初始化machine
    static {
        AI = new AtomicInteger();
        StringBuilder sb = new StringBuilder();
        int mac;
        try {
            Enumeration<NetworkInterface> eni = NetworkInterface.getNetworkInterfaces();
            while (eni.hasMoreElements()) {
                sb.append(eni.nextElement().toString());
            }
            mac = sb.toString().hashCode() << 16;

        } catch (Throwable e) {
            mac = (new SecureRandom().nextInt()) << 16;
        }

        int process = ManagementFactory.getRuntimeMXBean().getName().hashCode();

        ClassLoader loader = SysSeq.class.getClassLoader();
        int loaderId = loader != null ? System.identityHashCode(loader) : 0;

        sb.delete(0, sb.length());
        sb.append(Integer.toHexString(process));
        sb.append(Integer.toHexString(loaderId));
        process = sb.toString().hashCode() & 0xFFFF;

        machine = mac | process;
    }

    /**
     * 获取系统seq id ,24位长,前8位为系统时间秒值,后8位为原子id累加值,中间为机器进程识别码
     *
     * @return seq id
     */
    public String getSysSeq() {
        byte ab[] = new byte[12];
        ByteBuffer bb = ByteBuffer.wrap(ab);
        //前4位为系统时间秒值
        bb.putInt((int) (System.currentTimeMillis() / 1000));
        //中间4位为机器+进程识别码
        bb.putInt(machine);
        //后4位为原子id累加值
        bb.putInt(AI.incrementAndGet());

        //转换成16进制形式展示
        StringBuilder sb = new StringBuilder(24);
        for (final byte b : ab) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
