package com.EmailServiceAbstraction.Tests;

import com.EmailServiceAbstraction.models.EmailData;
import org.junit.Assert;
import org.junit.Test;

public class UnitTests {
    @Test
    public void EmailDataShouldSet() {
         EmailData emailData = new EmailData()
         {
        	 {
        		 this.from = "test@doma.com";
        	     this.to = "reci@gma.com";
        	     this.text = "plain text";
        	     this.html = "html text";
        	     this.subject = "tst subject";
        	 }
         };
        Assert.assertEquals((String)"From should be set", emailData.getFrom() == "test@doma.com");
        Assert.assertEquals((String)"to should be set", emailData.getFrom() == "reci@gma.com");
        Assert.assertEquals((String)"html text should be set", emailData.getFrom() == "html text");
    }
}