package spring;

public class VersionPrinter {

    private int majorVersion;
    private int minorVersion;

    public void print(){
        System.out.printf("이 프로그램의 버전은 %d.%d 입니다.\n",majorVersion,majorVersion);
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }
}
