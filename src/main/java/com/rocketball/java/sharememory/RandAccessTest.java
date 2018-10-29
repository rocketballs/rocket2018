//package com.rocketball.java.sharememory;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//
//public class RandAccessTest {
//    public static void main(String[] args) throws IOException {
//        RandomAccessFile RAFile = new RandomAccessFile("aaaa","r");
//// 获得相应的文件通道
//        FileChannel fc = RAFile.getChannel();
//// 取得文件的实际大小，以便映像到共享内存
//        long size = fc.size();
//// 获得共享内存缓冲区，该共享内存只读
//        FileChannel.MapMode.
//        MappedByteBuffer mapBuf = fc.map(FileChannel.MAP_RO,0,size);
//// 获得一个可读写的随机存取文件对象
//        RAFile = new RandomAccessFile(filename,"rw");
//// 获得相应的文件通道
//        fc = RAFile.getChannel();
//
//// 取得文件的实际大小，以便映像到共享内存
//        size = (int)fc.size();
//// 获得共享内存缓冲区，该共享内存可读写
//        mapBuf = fc.map(FileChannel.MAP_RW,0,size);
//// 获取头部消息：存取权限
//        mode = mapBuf.getInt();
//    }
//}
