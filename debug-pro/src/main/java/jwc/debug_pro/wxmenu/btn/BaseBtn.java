package jwc.debug_pro.wxmenu.btn;

import lombok.Getter;

@Getter
public class BaseBtn {
	protected String name;
	
	protected BaseBtn(String name) {
		super();
		this.name = name;
	}

}
