package cn.edu.jssvc.gezhi.colorlife.bean;

public class ArtsTheme {
    private  int themeId;
    private String themeName;

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public String toString() {
        return  themeId+","+themeName;
    }
}
