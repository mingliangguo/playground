public class NIOServer {

  public static void main(String[] args) throws IOException {
    ServerSocketChannel socketChannel = ServerSocketChannel.open();
    // 绑定端口
    socketChannel.bind(new InetSocketAddress(8080));
    // 声明异步
    socketChannel.configureBlocking(false);
    // 声明多路复用器，创建 linux下的epoll文件描述对象
    Selector selector = Selector.open();
    // 注册连接事件
    socketChannel.register(selector,SelectionKey.OP_ACCEPT);
    while (true){
      // 通过操作系统的epoll，选择触发事件的selectionKey 加入set集合中
      selector.select();
      final Set<SelectionKey> selectionKeys = selector.selectedKeys();
      final Iterator<SelectionKey> iterator = selectionKeys.iterator();
      // 遍历触发事件的selectionKey，selectionKey中包含 socketChannel
      while (iterator.hasNext()){
        final SelectionKey next = iterator.next();
        // 判断事件类型
        if(next.isAcceptable()){
          // 如果是连接事件，给对应的SocketChannel注册读取事件
          final ServerSocketChannel channel = (ServerSocketChannel) next.channel();
          final SocketChannel accept = channel.accept();
          accept.configureBlocking(false);
          accept.register(selector,SelectionKey.OP_READ);
        }else if (next.isReadable()){
          // 如果是读取事件，直接读取
          final SocketChannel channel = (SocketChannel) next.channel();
          ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
          final int read = channel.read(byteBuffer);
          System.out.println(new String(byteBuffer.array(),0,read));
        }
      }
      // 移除set集合中已经处理的socketChannel，避免二次处理
      iterator.remove();
    }
  }
}
// https://juejin.cn/post/6956516753752981535
