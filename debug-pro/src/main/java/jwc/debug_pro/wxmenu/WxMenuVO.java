package jwc.debug_pro.wxmenu;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jwc.debug_pro.wxmenu.menu.WxConditionalMenu;
import jwc.debug_pro.wxmenu.menu.WxMenu;
import lombok.Getter;
import lombok.Setter;

@Getter
public class WxMenuVO<T> extends WxResult {
	@Setter
	private WxMenu<T> menu;
	@JsonInclude(Include.NON_EMPTY)
	private List<WxConditionalMenu<T>> conditionalmenu;
	
	public void addConditionalMenu(WxConditionalMenu<T> menu) {
		if(conditionalmenu == null) {
			this.conditionalmenu = new ArrayList<>();
		}
		this.conditionalmenu.add(menu);
	}
}
