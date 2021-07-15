package com.ecodation.controller;

import java.util.Scanner;

import com.ecodation.dao.RegisterDao;
import com.ecodation.dto.RegisterDto;

public class RegisterController {

	public void Registration() throws InterruptedException {
		RegisterDao registerDao = new RegisterDao();
		RegisterDto dto = new RegisterDto();
		Scanner scan = new Scanner(System.in);

		System.out.println("Yükleniyor...");
		Thread.sleep(2000);

		System.out.println("------------------------");

		System.out.println("Mail: ");
		dto.setUser_mail(scan.nextLine());

		System.out.println("Þifre: ");
		dto.setUser_passw(scan.nextLine());

		dto = registerDao.login(dto);

		UserController userController = new UserController();
		AdminController adminController = new AdminController();

		if (registerDao.getLoginControl() == 1) {
			System.out.println("------------------------");
			System.out.println("Üye Bilgisi Bulunamadý Kayýt Olmak Ýçin Yönlendiriliyorsunuz...");
			System.out.println("------------------------");

			System.out.println("Ad: ");
			dto.setUser_name(scan.nextLine());

			System.out.println("Soyad: ");
			dto.setUser_sname(scan.nextLine());

			System.out.println("Þifre: ");
			dto.setUser_passw(scan.nextLine());

			System.out.println("Mail: ");
			dto.setUser_mail(scan.nextLine());

			registerDao.create(dto);
			dto = registerDao.login(dto);

			if (dto.getUser_status().equals("AKTÝF")) {
				if (dto.getUser_role().equals("MÜÞTERÝ")) {
					userController.UserProcess(dto.getUser_balance(), dto.getUser_mail(), dto.getUser_id());
				} else {
					adminController.AdminProcess(dto.getUser_balance(), dto.getUser_mail(), dto.getUser_id());
				}

			} else {
				System.out.println("Üyeliðiniz aktif deðil lütfen müþteri hizmetleri ile iletiþime geçiniz!");
			}

		} else {
			if (dto.getUser_status().equals("AKTÝF")) {
				if (dto.getUser_role().equals("MÜÞTERÝ")) {
					userController.UserProcess(dto.getUser_balance(), dto.getUser_mail(), dto.getUser_id());

				}

				else {
					adminController.AdminProcess(dto.getUser_balance(), dto.getUser_mail(), dto.getUser_id());
				}

			} else {
				System.out.println("Üyeliðiniz aktif deðil lütfen müþteri hizmetleri ile iletiþime geçiniz!");
			}

		}

		return;
	}
}
