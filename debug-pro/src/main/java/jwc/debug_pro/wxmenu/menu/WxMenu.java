package jwc.debug_pro.wxmenu.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;

@Getter
public class WxMenu<T> {
	protected List<T> button;
	@JsonInclude(Include.NON_NULL)
	protected Long menuid;
	
	public static <T> WxMenu<T> create(List<T> btns) {
		WxMenu<T> menu = new WxMenu<T>();
		menu.button = btns;
		return menu;
	}
}
