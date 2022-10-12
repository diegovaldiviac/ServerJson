package Other;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ChannelHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf tmp;


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("Handler added");
        tmp = ctx.alloc().buffer(4);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        //RequestData requestData = (RequestData) msg;
        //ResponseData responseData = new ResponseData();
        //responseData.setIntValue(requestData.getIntValue() * 2);
        ByteBuf asByteBuf = (ByteBuf) msg;
        ChannelFuture future = ctx.writeAndFlush(Unpooled.copiedBuffer("test", CharsetUtil.UTF_8));
        future.addListener(ChannelFutureListener.CLOSE);
        //System.out.println(requestData);
    }

    //Close connection between client and server
    public void closeConnections(ChannelHandlerContext ctx) {
        ctx.channel().close();
        ctx.channel().parent().close();
    }

}
