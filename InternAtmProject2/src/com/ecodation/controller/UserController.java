package com.ecodation.controller;

import java.util.Scanner;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ecodation.dao.LogDao;
import com.ecodation.dao.RegisterDao;
import com.ecodation.dao.TransferDao;
import com.ecodation.dto.RegisterDto;
import com.ecodation.dto.TransferDto;
import com.ecodation.controller.RegisterController;

public class UserController {

	private int uChoise;

	public void UserProcess(int b, String m, Long i) {
		System.out.println("------------------------");
		Scanner scan = new Scanner(System.in);
		RegisterDao registerDao = new RegisterDao();
		TransferDao transferDao = new TransferDao();
		TransferDto transferDto = new TransferDto();
		LogDao logDao = new LogDao();

		int number;
		boolean wh = false;
		boolean wh2 = false;

		while (wh2 == false) {
			System.out.println("------------------------");
			System.out.println("L�tfen yapmak istedi�iniz i�lemi se�iniz!");
			System.out.println("1-Para Yat�r");
			System.out.println("2-Para �ek");
			System.out.println("3-Havale Yap");
			System.out.println("4-Mail G�nder");
			System.out.println("5-��k�� Yap");
			uChoise = scan.nextInt();

			switch (uChoise) {
			case 1:
				System.out.println("Yat�rmak istedi�iniz miktar� giriniz...");
				number = scan.nextInt();
				b = b + number;

				registerDao.update(b, m, i);

				String setLog = ("Para Yat�rma ��lemi! Miktar: " + number);
				logDao.logUpdate(setLog, m);

				break;
			case 2:
				System.out.println("�ekmek istedi�iniz miktar� giriniz...");
				number = scan.nextInt();

				if (number > b) {
					while (!wh) {
						System.out.println("Yetersiz bakiye tekrar deneyin!");
						number = scan.nextInt();
						if (number < b) {
							wh = true;
							b = b - number;
							registerDao.update(b, m, i);

							setLog = ("Para �ekme ��lemi! Miktar: " + number);
							logDao.logUpdate(setLog, m);

						}
					}
				} else {
					b = b - number;

					registerDao.update(b, m, i);

					setLog = ("Para �ekme ��lemi! Miktar: " + number);
					logDao.logUpdate(setLog, m);
				}
				break;
			case 3:

				String transferM;
				int transferBalance;
				boolean wh3 = false;

				System.out.println("------------------------");
				System.out.println("Havale yapmak istedi�iniz mail adresini giriniz: ");
				String scanFix = scan.nextLine();
				System.out.println("------------------------");

				transferM = scan.nextLine();
				transferDto.setTransfer_mail(transferM);

				transferDto = transferDao.selectTransfer(transferDto);

				System.out.println("Havale yapmak istedi�iniz miktar: ");
				transferBalance = scan.nextInt();

				if (transferBalance > b) {
					while (!wh3) {
						System.out.println("Yetersiz bakiye tekrar deneyin!");
						System.out.println("Havale yapmak istedi�iniz miktar: ");
						transferBalance = scan.nextInt();
						if (transferBalance < b) {
							wh3 = true;
							b = b - transferBalance;
							registerDao.update(b, m, i);
							transferBalance = transferDto.getTransfer_balance() + transferBalance;
							transferDao.transferUpdate(transferBalance, transferM);

							setLog = ("Miktar: " + transferBalance + " --- " + transferM
									+ " kullan�c�s�na havale yap�ld�");
							logDao.logUpdate(setLog, m);

							System.out.println("------------------------");
							System.out.println("Ad: " + transferDto.getTransfer_name());
							System.out.println("Soyad: " + transferDto.getTransfer_sname());
							System.out.println("TRANSFER BA�ARIYLA YAPILDI!");

						}
					}
				} else {
					b = b - transferBalance;
					registerDao.update(b, m, i);
					transferBalance = transferDto.getTransfer_balance() + transferBalance;
					transferDao.transferUpdate(transferBalance, transferM);

					setLog = ("Miktar: " + transferBalance + " --- " + transferM + " kullan�c�s�na havale yap�ld�");
					logDao.logUpdate(setLog, m);

					System.out.println("------------------------");
					System.out.println("Ad: " + transferDto.getTransfer_name());
					System.out.println("Soyad: " + transferDto.getTransfer_sname());
					System.out.println("Miktar: " + transferBalance);
					System.out.println("TRANSFER BA�ARIYLA YAPILDI!");

				}
				break;

			case 4:
				String subject, text, toMail, fromMail, fromPassw;
				System.out.println("------------------------");
				String scanFix1 = scan.nextLine();

				System.out.println("Mail adresiniz: (Test �rne�i: ecodationstaj@gmail.com)");
				fromMail = scan.nextLine();
				System.out.println("------------------------");

				System.out.println("Mail �ifreniz: (Test �rne�i: ecodation2468)");
				fromPassw = scan.nextLine();
				System.out.println("------------------------");

				System.out.println("Mail Ba�l���: ");
				subject = scan.nextLine();

				System.out.println("------------------------");
				System.out.println("Mail Yaz�s�: ");
				text = scan.nextLine();

				System.out.println("------------------------");
				System.out.println("Kime: ");
				toMail = scan.nextLine();

				String to = toMail;

				String from = "ecodationstaj@gmail.com";

				String host = "smtp.gmail.com";

				Properties properties = System.getProperties();

				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.port", "465");
				properties.put("mail.smtp.ssl.enable", "true");
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.ssl.trust", "*");
				properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

				Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

						return new PasswordAuthentication(fromMail, fromPassw);

					}

				});

				session.setDebug(true);

				try {

					MimeMessage message = new MimeMessage(session);

					message.setFrom(new InternetAddress(from));

					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

					message.setSubject(subject);

					message.setText(text);

					System.out.println("G�nderiliyor...");

					Transport.send(message);

					setLog = (fromMail + " Kullan�c�s� " + toMail + " 'a mail yollad�!");
					logDao.logUpdate(setLog, m);

					System.out.println("------------------------");
					System.out.println("MA�L BA�ARIYLA G�NDER�LD�");
					System.out.println("------------------------");

				} catch (MessagingException mex) {
					mex.printStackTrace();
				}
				break;
			default:
				System.out.println("------------------------");
				System.out.println("BA�ARIYLA �IKI� YAPILDI!");
				System.out.println("------------------------");
				wh2 = true;
				break;

			}

		}

	}

}
