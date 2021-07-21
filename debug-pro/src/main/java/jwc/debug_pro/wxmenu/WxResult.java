package jwc.debug_pro.wxmenu;

import jwc.debug_pro.util.JsonUtil;
import lombok.Getter;

public class WxResult {
	protected int errcode;
	@Getter
	protected String errmsg;

	public boolean isSuccess() {
		return errcode == 0;
	}
	
	public static WxResult from(String resultJosn) {
		return JsonUtil.string2Obj(resultJosn, WxResult.class);
	}

}
