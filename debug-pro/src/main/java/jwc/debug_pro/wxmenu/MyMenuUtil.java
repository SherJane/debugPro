package jwc.debug_pro.wxmenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import jwc.debug_pro.util.JsonUtil;
import jwc.debug_pro.util.WxHttpUtil;
import jwc.debug_pro.wxmenu.btn.BaseBtn;
import jwc.debug_pro.wxmenu.btn.BtnType;
import jwc.debug_pro.wxmenu.btn.ClickTypeBtn;
import jwc.debug_pro.wxmenu.btn.ParentBtn;
import jwc.debug_pro.wxmenu.btn.ViewTypeBtn;
import jwc.debug_pro.wxmenu.menu.WxConditionalMenu;
import jwc.debug_pro.wxmenu.menu.WxMenu;

public class MyMenuUtil {
	private static final String CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	private static final String CREATE_CONDITIONAL_MENU = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=";
	private static final String QUERY_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
	private static final String DELETE_CONDITIONAL_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=";
	
	public static WxMenuVO<BaseBtn> test(String accessToken) {
		String resJosn = WxHttpUtil.get(QUERY_URL + accessToken);
		WxMenuVO<BaseBtn> curMenu = parseMenuJson(resJosn);
		
		//修改菜单
		
		return curMenu;
	}
	
	public static WxMenuVO<BaseBtn> queryMenu(int appType) throws Exception {
		String resJosn = WxHttpUtil.get(QUERY_URL + getAccessToken(appType));
		return parseMenuJson(resJosn);
	}

    public static WxResult createCommonMenu(int appType, WxMenu<BaseBtn> wxMenu){
    	String menuJson = JsonUtil.obj2String(wxMenu);
        String result = WxHttpUtil.postJson(CREATE_URL + getAccessToken(appType), menuJson, new HashMap<>());
        return WxResult.from(result);
    }
    
    public static WxResult createConditionalMenu(int appType, WxConditionalMenu<BaseBtn> wxMenu){
    	String menuJson = JsonUtil.obj2String(wxMenu);
        String result = WxHttpUtil.postJson(CREATE_CONDITIONAL_MENU + getAccessToken(appType), menuJson, new HashMap<>());
        return WxResult.from(result);
    }
    
    public static WxResult deleteConditionalMenu(int appType, long menuId) {
    	String result = WxHttpUtil.postJson(DELETE_CONDITIONAL_MENU + getAccessToken(appType), String.format("{\"menuid\":\"%s\"}", menuId), new HashMap<>());
        return WxResult.from(result);
    }
    
	public static final WxMenuVO<BaseBtn> parseMenuJson(String jsonString) {
		WxMenuVO<BaseBtn> result = new WxMenuVO<BaseBtn>();
		WxMenuVO<?> parseMenu = JsonUtil.string2Obj(jsonString, WxMenuVO.class);
		if(!parseMenu.isSuccess()) { 
			return null;
		}
		
		if(parseMenu.getMenu() != null) {
			WxMenu<BaseBtn> menu = WxMenu.create(parseBtns(parseMenu.getMenu().getButton()));
			result.setMenu(menu);
		}
		
		if(parseMenu.getConditionalmenu() != null) {
			for(WxConditionalMenu<?> cm : parseMenu.getConditionalmenu()) {
				result.addConditionalMenu(parseConditionalMenu(cm));
			}
		}
		
		return result;
	}
	
	private static <T> List<BaseBtn> parseBtns(Collection<T> btnObjs) {
		List<BaseBtn> btns = new ArrayList<>(btnObjs.size());
		for(Object o : btnObjs) {
			btns.add(parseBtn(o));
		}
		return btns;
	}
	
	private static BaseBtn parseBtn(Object btn) {
		String btnJsonStr = JsonUtil.obj2String(btn);
		Map<String,Object> map = JsonUtil.string2Obj(btnJsonStr, Map.class);
		String type = (String)map.get("type");
		if(!StringUtils.isEmpty(type)) {
			return parseBtnByType(type, btnJsonStr);
		}else {
			return parseParentBtn(btnJsonStr);
		}
	}
	
	private static BaseBtn parseBtnByType(String type, String btnJsonStr) {
		switch (type) {
		case BtnType.TYPE_CLICK:
			return JsonUtil.string2Obj(btnJsonStr, ClickTypeBtn.class);
		case BtnType.TYPE_VIEW:
			return JsonUtil.string2Obj(btnJsonStr, ViewTypeBtn.class);
		default:
			throw new UnsupportedOperationException(String.format("不支持解析type=%s的菜单按钮 btn=%s", type, btnJsonStr));
		}
	}
	
	private static ParentBtn<BaseBtn> parseParentBtn(String btnJsonStr) {
		ParentBtn<?> parsed = JsonUtil.string2Obj(btnJsonStr, ParentBtn.class);
		ParentBtn<BaseBtn> result = new ParentBtn<>(parsed.getName());
		result.setSub_button(parseBtns(parsed.getSub_button()));
		return result;
	}
	
	private static WxConditionalMenu<BaseBtn> parseConditionalMenu(WxConditionalMenu<?> cm) {
		WxConditionalMenu<BaseBtn> conditionalMenu = WxConditionalMenu.create(parseBtns(cm.getButton()), cm.getMenuid(), cm.getMatchrule());
		conditionalMenu.trySetTagId();
		return conditionalMenu;
	}
	
	private static String getAccessToken(int appType) {
		//TODO
		return null;
	}
}
