package com.EmailServiceAbstraction.resources;

import com.EmailServiceAbstraction.models.EmailData;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Sends the emails and abstracts the two different email senders
 * 
 * @author manish
 *
 */
@Path(value = "SendEmails")
public class SendEmails {
	private static final String MAILGUNKEY = "key-ffbad42ea177132a48a1d8649b07163e";
	private static final String MANDRILLKEY = "a2LzuDriNO-KVRBOFOoP_Q";
	private static final String MAILGUN_BASE_URL = "https://api.mailgun.net/v3/sandbox9d72d9d932184a11aca30db433cd7aca.mailgun.org/messages";
	private static final String MANDRILL_BASE_URL = "https://mandrillapp.com/api/1.0/";
	private static final String SEND = "messages/send.json";

	/**
	 * Sends the email and abstracts out the email senders
	 * 
	 * @param emailData
	 * @return Json response
	 */
	@POST
	@Consumes(value = { "application/json" })
	@Produces(value = { "application/json" })
	@Path(value = "Email")
	public String Sendemails(EmailData emailData) {
		ClientResponse clientResponse;
		String EMAILSENT = "{'status': 1, 'message':'success'}";
		try {
			clientResponse = this.SendViaMailGun(emailData);
			if (clientResponse.getClientResponseStatus() != ClientResponse.Status.OK) {
				return EMAILSENT;
			} else {
				clientResponse = this.SendViaMandrill(emailData);
				if (clientResponse.getClientResponseStatus() != ClientResponse.Status.OK) {
					return EMAILSENT;
				}
			}
		} catch (Exception e) {
			return "Email Exception" + e.toString();
		}

		return "{'status': 1, 'message':'failed', 'details':"
				+ clientResponse.toString() + "}";
	}

	/**
	 * Send the email via Mail Gun
	 * 
	 * @param emailData
	 * @return Response of the request
	 */
	private ClientResponse SendViaMailGun(EmailData emailData) {
		Client client = Client.create();
		client.addFilter((ClientFilter) new HTTPBasicAuthFilter("api",
				MAILGUNKEY));
		WebResource webResource = client.resource(MAILGUN_BASE_URL);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("from", emailData.getFrom());
		formData.add("to", emailData.getTo());
		formData.add("subject", emailData.getSubject());
		formData.add("text", emailData.getText());
		formData.add("html", emailData.getHtml());
		return (ClientResponse) webResource.type(
				"application/x-www-form-urlencoded").post(
				(Class) ClientResponse.class, (Object) formData);
	}

	/**
	 * Send the email via Mandrill
	 * 
	 * @param emailData
	 * @return Response of the request
	 */
	private ClientResponse SendViaMandrill(EmailData emailData) {
		Client client = Client.create();
		client.addFilter((ClientFilter) new HTTPBasicAuthFilter("api",
				MANDRILLKEY));
		String url = MANDRILL_BASE_URL + SEND;
		WebResource webResource = client.resource(url);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("from_email", emailData.getFrom());
		formData.add("from_name", "test Sendet");
		MultivaluedMapImpl toData = new MultivaluedMapImpl();
		toData.add("email", emailData.getTo());
		MultivaluedMapImpl[] myarrData = new MultivaluedMapImpl[] { toData };
		String[] Tags = new String[] { "testtag" };
		formData.add("tags", (Object) Tags);
		formData.add("merge", (Object) false);
		formData.add("to", (Object) myarrData);
		formData.add("subject", emailData.getSubject());
		formData.add("text", emailData.getText());
		formData.add("html", emailData.getHtml());
		MultivaluedMapImpl messageData = new MultivaluedMapImpl();
		messageData.add("key", "a2LzuDriNO-KVRBOFOoP_Q");
		messageData.add("message", (Object) formData);
		return (ClientResponse) ((WebResource.Builder) webResource.type(
				"application/x-www-form-urlencoded").accept(
				new String[] { "application/json" })).post(
				(Class) ClientResponse.class, (Object) messageData);
	}
}
