/**
 * 
 */
package com.tikie.common;

/**
 * @author zhaocs
 * 公共枚举集合
 */
public class CommonEnums {
	public static final int MQ_QUEUE_MIN_CODE = 1000;
	/**
	 * 功能：输出的json字段的值是StatusCode类决定
	 *      就是说返回状态的值是成功200还是失败400还是错误404，这些值
	 *      是StatusCode这个类定义的。
	 */
	public enum StatusCode {
	    /** 成功 */
	    SUCCESS(200, "成功"),

	    /** 没有登录 */
	    NOT_LOGIN(400, "没有登录"),

	    /** 发生异常 */
	    EXCEPTION(401, "发生异常"),

	    /** 系统错误 */
	    SYS_ERROR(402, "系统错误"),

	    /** 参数错误 */
	    PARAMS_ERROR(403, "参数错误 "),

	    /** 不支持或已经废弃 */
	    NOT_SUPPORTED(410, "不支持或已经废弃"),

	    /** AuthCode错误 */
	    INVALID_AUTHCODE(444, "无效的AuthCode"),

	    /** 太频繁的调用 */
	    TOO_FREQUENT(445, "太频繁的调用"),

	    /** 未知的错误 */
	    UNKNOWN_ERROR(499, "未知错误");

	    private int code;

	    private String message;

	    StatusCode(int code, String message) {
	        this.code = code;
	        this.message = message;
	    }

	    public int getCode() {
	        return code;
	    }

	    public String getMessage() {
	        return message;
	    }
	}
	
	// activemq枚举--小于1000为队列
	public enum MQDestination {
		// 文件处理业务消息队列
		FILE_QUEUE(10, "file.queue"),
		// 文件处理业务消息队列--一个消费者消费完所有消息
		FILE_QUEUE_EXCLUSIVE(11, "file.queue?consumer.exclusive=true"),
		FILE_QUEUE_EXCLUSIVE_PRIORITY9(12, "file.queue?consumer.exclusive=true&consumer.priority=9"),
		
		// 删除到回收站
		FILE_QUEUE_REBACK(13, "file.reback.queue"),
		// 文件处理业务消息队列--一个消费者消费完所有消息
		FILE_QUEUE_REBACK_EXCLUSIVE(14, "file.reback.queue?consumer.exclusive=true"),
		FILE_QUEUE_REBACK_EXCLUSIVE_PRIORITY9(15, "file.reback.queue?consumer.exclusive=true&consumer.priority=9"),
		
		// 彻底删除
		FILE_QUEUE_DEL(16, "file.del.queue"),
		// 文件处理业务消息队列--一个消费者消费完所有消息
		FILE_QUEUE_DEL_EXCLUSIVE(17, "file.del.queue?consumer.exclusive=true"),
		FILE_QUEUE_DEL_EXCLUSIVE_PRIORITY9(18, "file.del.queue?consumer.exclusive=true&consumer.priority=9"),
		
		OTHER_QUEUE(100, "other.queue"),
		OTHER_QUEUE_EXCLUSIVE(101, "other.queue?consumer.exclusive=true"),
		OTHER_QUEUE_EXCLUSIVE_PRIORITY9(102, "other.queue?consumer.exclusive=true&consumer.priority=9"),
		
		// 文件处理业务消息队列
		FILE_TOPIC(1000, "file.topic"),
				
		OTHER_TOPIC(9999, "other.queue");
		
		private int code;
	    private String message;

	    MQDestination(int code, String message) {
	        this.code = code;
	        this.message = message;
	    }

	    public int getCode() {
	        return code;
	    }

	    public String getMessage() {
	        return message;
	    }
	}
	
	// 缩略图
	public enum FileTreeThumbnail{
		// 图片类型
		IMAGE("fileicon-position fileicon-small-pic", "image"),
		// 文件夹类型类型
		FOLDER("fileicon-position fileicon-small-foler", "folder");
		
		private String css;

	    private String type;

	    FileTreeThumbnail(String css, String type) {
	        this.css = css;
	        this.type = type;
	    }

	    public String getCss() {
	        return css;
	    }

	    public String getType() {
	        return type;
	    }
	}
}
