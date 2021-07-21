package jwc.debug_pro.wxmenu.menu;

import java.util.List;

import lombok.Getter;

@Getter
public class WxConditionalMenu<T> extends WxMenu<T> {
	private MatchRule matchrule;
	
	@Getter
	private static class MatchRule {
		private Integer group_id;
		private Integer tag_id;
	}
	
	//个性化菜单查询时返回group_id,创建时又是以tag_id的名字
	public void trySetTagId() {
		if(matchrule == null) {
			return;
		}
		if(matchrule.group_id != null && matchrule.tag_id == null) {
			matchrule.tag_id = matchrule.group_id;
		}
	}
	
	public static <T> WxConditionalMenu<T> create(List<T> btns, Long menuId, MatchRule matchRule) {
		WxConditionalMenu<T> menu = new WxConditionalMenu<T>();
		menu.button = btns;
		menu.menuid = menuId;
		menu.matchrule = matchRule;
		return menu;
	}
}
