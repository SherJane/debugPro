package jwc.debug_pro.wxmenu.btn;

import lombok.Setter;

public class ClickTypeBtn extends BaseBtnWithType {
	@Setter
	private String key;
	
	protected ClickTypeBtn(String name, String key) {
		super(name, BtnType.TYPE_CLICK);
		this.key = key;
	}

}
