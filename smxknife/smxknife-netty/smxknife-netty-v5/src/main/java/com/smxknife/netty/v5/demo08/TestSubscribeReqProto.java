package com.smxknife.netty.v5.demo08;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

/**
 * @author smxknife
 * 2018-12-05
 */
public class TestSubscribeReqProto {
	private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
		return req.toByteArray();
	}

	private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
		return SubscribeReqProto.SubscribeReq.parseFrom(body);
	}

	private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		return builder
				.setSubReqId(1)
				.setUserName("admin")
				.setProductName("Netty welcome")
				.addAllAddress(Arrays.asList("NanJin", "BeiJing", "ShenZhen"))
				.build();
	}

	public static void main(String[] args) throws InvalidProtocolBufferException {
		SubscribeReqProto.SubscribeReq req = createSubscribeReq();
		System.out.println("before encode : " + req.toString());
		System.out.println("after encode : " + encode(req));
		SubscribeReqProto.SubscribeReq decode = decode(encode(req));
		System.out.println("decode : " + decode);
		System.out.println("after decode : " + req.toString());
		System.out.println("Assert equal : " + decode.equals(req));
	}
}
