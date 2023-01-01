package main;

import assembler.MemberServiceAssembler;
import spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static MemberServiceAssembler assembler = new MemberServiceAssembler();

    public static void main(String[] args) throws IOException {
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
        MemberRegisterService memberRegisterService = assembler.getRegisterService();
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
        ChangePasswordService changePasswordService = assembler.getChangePasswordService();
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

    private static void printHelp(){
        System.out.println("명령어 사용법");
        System.out.println("new 이메일 암호 확인 이름 순으로 입력");
        System.out.println("change 이메일 기존패스워드 새패스워드 순으로 입력");
    }

}
