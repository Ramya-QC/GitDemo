package com.tradeleaves.marketplace.pages;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.sikuli.script.FindFailed;

import com.tradeleaves.marketplace.InitProperties;
import com.tradeleaves.marketplace.utility.Utility;

import jxl.read.biff.BiffException;

public class UICheckOnComreg {

	//Go through Supplier user registration, Company Registration, Subscription & Product posting for US site
		public void doUICheckOnComreg() throws BiffException, ClassNotFoundException, IOException, InterruptedException, SQLException, AWTException, FindFailed
		{
		Utility.KeywordDataExecution(InitProperties.getConfigProperty(InitProperties.WBBOOK), InitProperties.getConfigProperty(InitProperties.SHEET_UICHECKON_COMPREG));
		}
}
