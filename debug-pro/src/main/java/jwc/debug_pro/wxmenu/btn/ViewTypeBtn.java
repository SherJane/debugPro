package jwc.debug_pro.wxmenu.btn;

import lombok.Setter;

public class ViewTypeBtn extends BaseBtnWithType {
	@Setter
	private String url;

	protected ViewTypeBtn(String name, String url) {
		super(name, BtnType.TYPE_VIEW);
		this.url = url;
	}
	
}
