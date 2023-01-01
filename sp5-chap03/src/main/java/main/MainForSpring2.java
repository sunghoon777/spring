package main;

import config.AppContext;
import config.AppContext1;
import config.AppContext2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForSpring2 {

    public static  AnnotationConfigApplicationContext context = null;

    public static void main(String[] args) throws IOException {
        context = new AnnotationConfigApplicationContext(AppContext1.class, AppContext2.class);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("명령어를 입력해주세요");
            String command = bufferedReader.readLine();
            if(command.equalsIgnoreCase("exit")){
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            // 이메일 암호 확인 이름순으로 입력
            else if(command.startsWith("new ")){
                processNewCommand(command.split(" "));
            }
            // 이메일 기존패스워드 새패스워드 순으로 입력
            else if(command.startsWith("change ")){
                processChangeCommand(command.split(" "));
            }
            else if(command.equals("list")){
                processListCommand();
            }
            else if(command.startsWith("info ")){
                processInfoCommand(command.split(" "));
            }
            else if(command.equals("version")){
                processVersionCommand();
            }
            else{
                printHelp();
            }
        }

    }

    private static void processNewCommand(String[] s) {
        if(s.length != 5){
            printHelp();
            return;
        }
        if(!s[2].equals(s[3])){
            System.out.println("패스워드와 패스워드 확인이 일치하지 않습니다.");
            return;
        }
        MemberRegisterService memberRegisterService = context.getBean("memberRegisterService",MemberRegisterService.class);
        try {
            memberRegisterService.regist(new RegisterRequest(s[1],s[2],s[3],s[4]));
            System.out.println("등록 완료");
        }catch (ExistingMemberException e){
            System.out.println("기존에 존재하는 멤버입니다.");
        }
    }

    private static void processChangeCommand(String[] s) {
        if(s.length != 4){
            printHelp();
            return;
        }
        ChangePasswordService changePasswordService = context.getBean("changePasswordService",ChangePasswordService.class);
        try {
            changePasswordService.changePassword(s[1],s[2],s[3]);
            System.out.println("패스워드 변경 완료");
        }
        catch (MemberNotFoundException e){
            System.out.println("해당되는 멤버는 존재하지 않습니다.");
        }
        catch (WrongPasswordException e){
            System.out.println("기존 패스워드가 일치하지 않습니다.");
        }
    }

    private static void processListCommand() {
        MemberListPrinter memberListPrinter = context.getBean("memberListPrinter",MemberListPrinter.class);
        memberListPrinter.printAll();
    }

    private static void processInfoCommand(String[] s) {
        if(s.length != 2){
            printHelp();
            return;
        }
        MemberInfoPrinter memberInfoPrinter = context.getBean("memberInfoPrinter",MemberInfoPrinter.class);
        memberInfoPrinter.printMemberInfo(s[1]);
    }

    private static void processVersionCommand() {
        VersionPrinter versionPrinter = context.getBean("versionPrinter",VersionPrinter.class);
        versionPrinter.print();
    }

    private static void printHelp(){
        System.out.println("명령어 사용법");
        System.out.println("new 이메일 암호 확인 이름 순으로 입력");
        System.out.println("change 이메일 기존패스워드 새패스워드 순으로 입력");
        System.out.println("list");
        System.out.println("info 이메일");
        System.out.println("version");
    }
    
}
