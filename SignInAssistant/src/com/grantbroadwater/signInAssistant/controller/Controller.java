package com.grantbroadwater.signInAssistant.controller;

import java.util.ArrayList;
import java.util.Arrays;

import com.grantbroadwater.school.Administrator;
import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.school.Student;
import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.View;

public class Controller {

	Model model;
	View view;
	
	AdminSaveActionListener adminSaveActionListern;
	AdminSignInListener adminSignInListener;
	AdminStartSignInListener adminStartSignInListener;
	DataDocumentListener dataDocumentListener;
	InquireDocumentListener inquireDocumentListener;
	InquireListSelectionListener inquireListSelectionListener;
	StudentPinDocumentListener studentPinDocumnetListener;
	StudentSignInActionListener studentSignInActionListener;
	
	public Controller(){
		
	}

	public void createModelAndView(){
		model = new Model();
		model.loadData();
		
		view = new View(new ArrayList<BellSchedule>(Arrays.asList(model.getSchedules())));
		
		adminSaveActionListern = new AdminSaveActionListener(this);
		adminSignInListener = new AdminSignInListener(model, view.getAdministratorSignInPanel(), this);
		adminStartSignInListener = new AdminStartSignInListener(model, view.getAdministratorPanel(), this);
		dataDocumentListener = new DataDocumentListener(model, view.getDataPanel());
		inquireDocumentListener = new InquireDocumentListener(model, view.getInquirePanel());
		inquireListSelectionListener = new InquireListSelectionListener(model, view.getInquirePanel());
		studentPinDocumnetListener = new StudentPinDocumentListener(model, view.getStudentPanel());
		studentSignInActionListener = new StudentSignInActionListener(model, view.getStudentPanel(), this);
	
		view.addSaveActionListener(adminSignInListener);
		view.addSignInActionListener(adminSignInListener);
		view.addStartActionListener(adminStartSignInListener);
		view.addDataPanelDocumentListener(dataDocumentListener);
		view.addInquirePanelDocumentListener(inquireDocumentListener);
		view.addInquirePanelListSelectionListener(inquireListSelectionListener);
		view.addStudentPanelDocumentListener(studentPinDocumnetListener);
		view.addStudentPanelConfirmListener(studentSignInActionListener);
	}
	
	public void startApplication(){
		view.setSIAFrameVisible(true);
	}
	
	public Model getModel(){
		return model;
	}
	
	public View getView(){
		return this.view;
	}
	
	
	/* ----- Behaviors too large to be handled by listeners ----- */
	
	protected void signInAdmin(Administrator admin){
		System.out.println(admin.getFirstName() + " to be signed in");
	}
	
	protected void punchStudent(Student student){
		System.out.println(student.getFirstName() + " to be punched");
	}
	
	protected void startStudentSignIn(BellSchedule schedule){
		System.out.println("Starting sign in with schedule: "+schedule.getName());
	}
	
	protected void stopStudentSignIn(){
		System.out.println("Now closing student sign in");
	}
	
	protected void saveSignInSheet(){
		System.out.println("Save the sign in sheet");
	}
	
}
