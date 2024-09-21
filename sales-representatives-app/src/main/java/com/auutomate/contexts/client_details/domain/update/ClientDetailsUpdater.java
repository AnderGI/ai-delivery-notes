package com.auutomate.contexts.client_details.domain.update;

import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsBankAccount;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.ClientMail;
import com.auutomate.contexts.client_details.domain.ClientNID;
import com.auutomate.contexts.client_details.domain.ClientName;
import com.auutomate.contexts.shared.domain.EventBus;

public final class ClientDetailsUpdater {

	public static void update(ClientDetailsRepository repo, EventBus bus, String idValue, String nameValue,
			String nidValue, String mailValue, String bankAccountValue, String billingAddressNameValue,
			String billingPopulationValue, Integer billingPostalCodeValue, String billingProvinceValue,
			String deliveryAddressNameValue, String deliveryPopulationValue, Integer deliveryPostalCodeValue,
			String deliveryProvinceValue, ClientDetails clientDetails) {

		if (!isNameEqual(clientDetails, nameValue)) {
			clientDetails.updateName(nameValue);
			bus.publish(new ClientNameUpdatedDomainEvent(clientDetails.idValue(), clientDetails.nameValue()));
		}

		if (!isNidEqual(clientDetails, nidValue)) {
			clientDetails.updateNid(nidValue);
			bus.publish(new ClientNidUpdatedDomainEvent(clientDetails.idValue(), clientDetails.nidValue()));
		}

		if (!isMailEqual(clientDetails, mailValue)) {
			clientDetails.updateMail(mailValue);
			bus.publish(new ClientMailUpdatedDomainEvent(clientDetails.idValue(), clientDetails.mailValue()));
		}

		if (!isBankAccountEqual(clientDetails, bankAccountValue)) {
			clientDetails.updateBankAccount(bankAccountValue);
			bus.publish(new ClientBankAccountUpdatedDomainEvent(clientDetails.idValue(), clientDetails.bankAccountValue()));
		}
		
		repo.save(clientDetails);

	}

	private static boolean isBankAccountEqual(ClientDetails clientDetails, String bankAccountValue) {
		ClientDetailsBankAccount currentValue = new ClientDetailsBankAccount(clientDetails.bankAccountValue());
		ClientDetailsBankAccount newValue = new ClientDetailsBankAccount(bankAccountValue);
		return currentValue.equals(newValue);
	}

	private static boolean isMailEqual(ClientDetails clientDetails, String mailValue) {
		ClientMail currentValue = new ClientMail(clientDetails.mailValue());
		ClientMail newValue = new ClientMail(mailValue);
		return currentValue.equals(newValue);
	}

	private static boolean isNidEqual(ClientDetails clientDetails, String nidValue) {
		ClientNID currentValue = new ClientNID(clientDetails.nidValue());
		ClientNID newValue = new ClientNID(nidValue);
		return currentValue.equals(newValue);
	}

	private static boolean isNameEqual(ClientDetails clientDetails, String nameValue) {
		ClientName currentValue = new ClientName(clientDetails.nameValue());
		ClientName newValue = new ClientName(nameValue);
		return currentValue.equals(newValue);
	}

}
