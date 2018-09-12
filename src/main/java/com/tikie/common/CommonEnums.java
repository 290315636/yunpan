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
	
	public static final String STRING_IMAGE = "image";
	public static final String STRING_DOCUMENT = "document";
	public static final String STRING_VIDEO = "video";
    public static final String STRING_MUSIC = "music";
    public static final String STRING_OTHER = "other";
    public static final String STRING_FOLDER = "folder";
	
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
        PNG("fileicon-position fileicon-small-pic", FileType.PNG.getTinyType()),
        JPG("fileicon-position fileicon-small-pic", FileType.JPG.getTinyType()),
        ICO("fileicon-position fileicon-small-pic", FileType.ICO.getTinyType()),
        JPEG("fileicon-position fileicon-small-pic", FileType.JPEG.getTinyType()),
        GIF("fileicon-position fileicon-small-pic", FileType.GIF.getTinyType()),
        SVG("fileicon-position fileicon-small-pic", FileType.SVG.getTinyType()),
        PSD("fileicon-position fileicon-small-pic", FileType.PSD.getTinyType()),
        
        BMP("fileicon-position fileicon-small-pic", FileType.BMP.getTinyType()),
        TIFF("fileicon-position fileicon-small-pic", FileType.TIFF.getTinyType()),
        TIF("fileicon-position fileicon-small-pic", FileType.TIF.getTinyType()),
        PCX("fileicon-position fileicon-small-pic", FileType.PCX.getTinyType()),
        TGA("fileicon-position fileicon-small-pic", FileType.TGA.getTinyType()),
        EXIF("fileicon-position fileicon-small-pic", FileType.EXIF.getTinyType()),
        FPX("fileicon-position fileicon-small-pic", FileType.FPX.getTinyType()),
        CDR("fileicon-position fileicon-small-pic", FileType.CDR.getTinyType()),
        PCD("fileicon-position fileicon-small-pic", FileType.PCD.getTinyType()),
        DXF("fileicon-position fileicon-small-pic", FileType.DXF.getTinyType()),
        UFO("fileicon-position fileicon-small-pic", FileType.UFO.getTinyType()),
        EPS("fileicon-position fileicon-small-pic", FileType.EPS.getTinyType()),
        AI("fileicon-position fileicon-small-pic", FileType.AI.getTinyType()),
        RAW("fileicon-position fileicon-small-pic", FileType.RAW.getTinyType()),
        WMF("fileicon-position fileicon-small-pic", FileType.WMF.getTinyType()),
        WEBP("fileicon-position fileicon-small-pic", FileType.WEBP.getTinyType()),
        HDRI("fileicon-position fileicon-small-pic", FileType.HDRI.getTinyType()),
        FLIC("fileicon-position fileicon-small-pic", FileType.FLIC.getTinyType()),
        EMF("fileicon-position fileicon-small-pic", FileType.EMF.getTinyType()),
        
        // 文档类型
        DOC("fileicon-position fileicon-small-word", FileType.DOC.getTinyType()),
        DOCX("fileicon-position fileicon-small-word", FileType.DOCX.getTinyType()),
        PDF("fileicon-position fileicon-small-ppt", FileType.PDF.getTinyType()),
        XLS("fileicon-position fileicon-small-excel-red", FileType.XLS.getTinyType()),
        XLSX("fileicon-position fileicon-small-excel-red", FileType.XLSX.getTinyType()),
        PPT("fileicon-position fileicon-small-ppt", FileType.PPT.getTinyType()),
        PPTX("fileicon-position fileicon-small-ppt", FileType.PPTX.getTinyType()),
        RTF("fileicon-position fileicon-small-document", FileType.RTF.getTinyType()),
        TXT("fileicon-position fileicon-small-txt", FileType.TXT.getTinyType()),
        LOG("fileicon-position fileicon-small-document", FileType.LOG.getTinyType()),
        SH("fileicon-position fileicon-small-code", FileType.SH.getTinyType()),
        SQL("fileicon-position fileicon-small-document", FileType.SQL.getTinyType()),
        HTM("fileicon-position fileicon-small-html", FileType.HTM.getTinyType()),
        HTML("fileicon-position fileicon-small-html", FileType.HTML.getTinyType()),
        XML("fileicon-position fileicon-small-html", FileType.XML.getTinyType()),
        WPD("fileicon-position fileicon-small-document", FileType.WPD.getTinyType()),
        
        // 视频类型
        AVI("fileicon-position fileicon-small-video", FileType.AVI.getTinyType()),
        WMV("fileicon-position fileicon-small-video", FileType.WMV.getTinyType()),
        MPEG("fileicon-position fileicon-small-video", FileType.MPEG.getTinyType()),
        MPEG1("fileicon-position fileicon-small-video", FileType.MPEG1.getTinyType()),
        MPEG2("fileicon-position fileicon-small-video", FileType.MPEG2.getTinyType()),
        MPEG3("fileicon-position fileicon-small-video", FileType.MPEG3.getTinyType()),
        MPEG4("fileicon-position fileicon-small-video", FileType.MPEG4.getTinyType()),
        MPG("fileicon-position fileicon-small-video", FileType.MPG.getTinyType()),
        MPE("fileicon-position fileicon-small-video", FileType.MPE.getTinyType()),
        MPA("fileicon-position fileicon-small-video", FileType.MPA.getTinyType()),
        M15("fileicon-position fileicon-small-video", FileType.M15.getTinyType()),
        M1V("fileicon-position fileicon-small-video", FileType.M1V.getTinyType()),
        MP4("fileicon-position fileicon-small-video", FileType.MP4.getTinyType()),
        MOV("fileicon-position fileicon-small-video", FileType.MOV.getTinyType()),
        MKV("fileicon-position fileicon-small-video", FileType.MKV.getTinyType()),
        MTV("fileicon-position fileicon-small-video", FileType.MTV.getTinyType()),
        FLV("fileicon-position fileicon-small-video", FileType.FLV.getTinyType()),
        F4V("fileicon-position fileicon-small-video", FileType.F4V.getTinyType()),
        M4V("fileicon-position fileicon-small-video", FileType.M4V.getTinyType()),
        RMVB("fileicon-position fileicon-small-video", FileType.RMVB.getTinyType()),
        RM("fileicon-position fileicon-small-video", FileType.RM.getTinyType()),
        VIDEO3GP("fileicon-position fileicon-small-video", FileType.VIDEO3GP.getTinyType()),
        DAT("fileicon-position fileicon-small-video", FileType.DAT.getTinyType()),
        TS("fileicon-position fileicon-small-video", FileType.TS.getTinyType()),
        MTS("fileicon-position fileicon-small-video", FileType.MTS.getTinyType()),
        VOB("fileicon-position fileicon-small-video", FileType.VOB.getTinyType()),
        AMV("fileicon-position fileicon-small-video", FileType.AMV.getTinyType()),
        DMV("fileicon-position fileicon-small-video", FileType.DMV.getTinyType()),
        ASF("fileicon-position fileicon-small-video", FileType.ASF.getTinyType()),
        SWF("fileicon-position fileicon-small-video", FileType.SWF.getTinyType()),
        
        // 种子类型
        GRAIN("fileicon-position fileicon-small-grain", FileType.GRAIN.getTinyType()),
        
        // 音乐类型
        MP3("fileicon-position fileicon-small-music", FileType.MP3.getTinyType()),
        WMA("fileicon-position fileicon-small-music", FileType.WMA.getTinyType()),
        FLAC("fileicon-position fileicon-small-music", FileType.FLAC.getTinyType()),
        AAC("fileicon-position fileicon-small-music", FileType.AAC.getTinyType()),
        MMF("fileicon-position fileicon-small-music", FileType.MMF.getTinyType()),
        AMR("fileicon-position fileicon-small-music", FileType.AMR.getTinyType()),
        M4A("fileicon-position fileicon-small-music", FileType.M4A.getTinyType()),
        M4R("fileicon-position fileicon-small-music", FileType.M4R.getTinyType()),
        OGG("fileicon-position fileicon-small-music", FileType.OGG.getTinyType()),
        MP2("fileicon-position fileicon-small-music", FileType.MP2.getTinyType()),
        WAV("fileicon-position fileicon-small-music", FileType.WAV.getTinyType()),
        WV("fileicon-position fileicon-small-music", FileType.WV.getTinyType()),
	    
        // 其他类型
        EXE("fileicon-position fileicon-small-yun", FileType.EXE.getTinyType()),
        BAT("fileicon-position fileicon-small-yun", FileType.BAT.getTinyType()),
        ZIP("fileicon-position fileicon-small-zip", FileType.ZIP.getTinyType()),
        RAR("fileicon-position fileicon-small-zip", FileType.RAR.getTinyType()),
        ISO("fileicon-position fileicon-small-yun", FileType.ISO.getTinyType()),
        GZ("fileicon-position fileicon-small-zip", FileType.GZ.getTinyType()),
        CFG("fileicon-position fileicon-small-yun", FileType.CFG.getTinyType()),
        OTHER("fileicon-position fileicon-small-other", FileType.OTHER.getTinyType()),
		// 文件夹类型类型
		FOLDER("fileicon-position fileicon-small-folder", FileType.FOLDER.getTinyType());
		
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
	
	public enum FileType{
        // 图片类型
	    PNG("png", CommonEnums.STRING_IMAGE),
	    JPG("jpg", CommonEnums.STRING_IMAGE),
	    ICO("ico", CommonEnums.STRING_IMAGE),
	    JPEG("jpeg", CommonEnums.STRING_IMAGE),
	    GIF("gif", CommonEnums.STRING_IMAGE),
	    SVG("svg", CommonEnums.STRING_IMAGE),
	    PSD("psd", CommonEnums.STRING_IMAGE),
	    
	    BMP("bmp", CommonEnums.STRING_IMAGE),
	    TIFF("tiff", CommonEnums.STRING_IMAGE),
	    TIF("tif", CommonEnums.STRING_IMAGE),
	    PCX("pcx", CommonEnums.STRING_IMAGE),
	    TGA("tga", CommonEnums.STRING_IMAGE),
	    EXIF("exif", CommonEnums.STRING_IMAGE),
	    FPX("fpx", CommonEnums.STRING_IMAGE),
	    CDR("cdr", CommonEnums.STRING_IMAGE),
	    PCD("pcd", CommonEnums.STRING_IMAGE),
	    DXF("dxf", CommonEnums.STRING_IMAGE),
	    UFO("ufo", CommonEnums.STRING_IMAGE),
	    EPS("eps", CommonEnums.STRING_IMAGE),
	    AI("ai", CommonEnums.STRING_IMAGE),
	    RAW("raw", CommonEnums.STRING_IMAGE),
	    WMF("wmf", CommonEnums.STRING_IMAGE),
	    WEBP("webp", CommonEnums.STRING_IMAGE),
	    HDRI("hdri", CommonEnums.STRING_IMAGE),
	    FLIC("flic", CommonEnums.STRING_IMAGE),
	    EMF("emf", CommonEnums.STRING_IMAGE),
	    
        // 文档类型
	    DOC("doc", CommonEnums.STRING_DOCUMENT),
	    DOCX("docx", CommonEnums.STRING_DOCUMENT),
	    PDF("pdf", CommonEnums.STRING_DOCUMENT),
	    XLS("xls", CommonEnums.STRING_DOCUMENT),
	    XLSX("xlsx", CommonEnums.STRING_DOCUMENT),
	    PPT("ppt", CommonEnums.STRING_DOCUMENT),
	    PPTX("pptx", CommonEnums.STRING_DOCUMENT),
	    RTF("rtf", CommonEnums.STRING_DOCUMENT),
	    TXT("txt", CommonEnums.STRING_DOCUMENT),
	    LOG("log", CommonEnums.STRING_DOCUMENT),
	    SH("log", CommonEnums.STRING_DOCUMENT),
	    SQL("sql", CommonEnums.STRING_DOCUMENT),
	    HTM("htm", CommonEnums.STRING_DOCUMENT),
	    HTML("html", CommonEnums.STRING_DOCUMENT),
	    XML("xml", CommonEnums.STRING_DOCUMENT),
	    WPD("wpd", CommonEnums.STRING_DOCUMENT),
        
        // 视频类型
	    AVI("avi", CommonEnums.STRING_VIDEO),
	    WMV("wmv", CommonEnums.STRING_VIDEO),
	    MPEG("mpeg", CommonEnums.STRING_VIDEO),
	    MPEG1("mpeg1", CommonEnums.STRING_VIDEO),
	    MPEG2("mpeg2", CommonEnums.STRING_VIDEO),
	    MPEG3("mpeg3", CommonEnums.STRING_VIDEO),
	    MPEG4("mpeg4", CommonEnums.STRING_VIDEO),
	    MPG("mpg", CommonEnums.STRING_VIDEO),
	    MPE("mpe", CommonEnums.STRING_VIDEO),
	    MPA("mpa", CommonEnums.STRING_VIDEO),
	    M15("m15", CommonEnums.STRING_VIDEO),
	    M1V("m1v", CommonEnums.STRING_VIDEO),
	    MP4("mp4", CommonEnums.STRING_VIDEO),
	    MOV("mov", CommonEnums.STRING_VIDEO),
	    MKV("mkv", CommonEnums.STRING_VIDEO),
	    MTV("mtv", CommonEnums.STRING_VIDEO),
	    FLV("flv", CommonEnums.STRING_VIDEO),
	    F4V("f4v", CommonEnums.STRING_VIDEO),
	    M4V("m4v", CommonEnums.STRING_VIDEO),
	    RMVB("rmvb", CommonEnums.STRING_VIDEO),
	    RM("rm", CommonEnums.STRING_VIDEO),
	    VIDEO3GP("3gp", CommonEnums.STRING_VIDEO),
	    DAT("dat", CommonEnums.STRING_VIDEO),
	    TS("ts", CommonEnums.STRING_VIDEO),
	    MTS("mts", CommonEnums.STRING_VIDEO),
	    VOB("vob", CommonEnums.STRING_VIDEO),
	    AMV("amv", CommonEnums.STRING_VIDEO),
	    DMV("dmv", CommonEnums.STRING_VIDEO),
	    ASF("asf", CommonEnums.STRING_VIDEO),
	    SWF("swf", CommonEnums.STRING_VIDEO),
	    
        // 种子类型
        GRAIN("torrent", "grain"),
        
        // 音乐类型
        MP3("mp3", CommonEnums.STRING_MUSIC),
        WMA("wma", CommonEnums.STRING_MUSIC),
        FLAC("flac", CommonEnums.STRING_MUSIC),
        AAC("aac", CommonEnums.STRING_MUSIC),
        MMF("mmf", CommonEnums.STRING_MUSIC),
        AMR("amr", CommonEnums.STRING_MUSIC),
        M4A("m4a", CommonEnums.STRING_MUSIC),
        M4R("m4r", CommonEnums.STRING_MUSIC),
        OGG("ogg", CommonEnums.STRING_MUSIC),
        MP2("mp2", CommonEnums.STRING_MUSIC),
        WAV("wav", CommonEnums.STRING_MUSIC),
        WV("wv", CommonEnums.STRING_MUSIC),
        
        // 其他类型
        EXE("exe", CommonEnums.STRING_OTHER),
        BAT("bat", CommonEnums.STRING_OTHER),
        ZIP("zip", CommonEnums.STRING_OTHER),
        RAR("rar", CommonEnums.STRING_OTHER),
        ISO("iso", CommonEnums.STRING_OTHER),
        GZ("gz", CommonEnums.STRING_OTHER),
        CFG("cfg", CommonEnums.STRING_OTHER),
        OTHER(CommonEnums.STRING_OTHER, CommonEnums.STRING_OTHER),
        // 文件夹类型类型
        FOLDER(CommonEnums.STRING_FOLDER, CommonEnums.STRING_FOLDER);
        
        private String tinyType;

        private String type;

        FileType(String tinyType, String type) {
            this.tinyType = tinyType;
            this.type = type;
        }

        public String getTinyType() {
            return tinyType;
        }

        public String getType() {
            return type;
        }
    }
	
	public enum FileCount{
	    IMAGE(CommonEnums.STRING_IMAGE, 0),
	    DOCUMENT(CommonEnums.STRING_DOCUMENT, 0),
	    VIDEO(CommonEnums.STRING_VIDEO, 0),
	    GRAIN("grain", 0),
	    MUSIC(CommonEnums.STRING_MUSIC, 0),
	    SHARE("share", 0),
	    TRASH("trash", 0),
	    FOLDER(CommonEnums.STRING_FOLDER, 0),
	    OTHER(CommonEnums.STRING_OTHER, 0);
	    private String type;

        private int init;

        FileCount(String type, int init) {
            this.type = type;
            this.init = init;
        }

        public String getType() {
            return type;
        }

        public int getInit() {
            return init;
        }
	}
}
