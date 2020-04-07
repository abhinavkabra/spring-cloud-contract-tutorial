package contracts

org.springframework.cloud.contract.spec.Contract.make {
    label 'newCustomerAdded'
    input {
        triggeredBy('newCustomerAddedTriggered()')
    }
    outputMessage {
        sentTo('newCustomerAdded')
        body([
                customerId: $(stub('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'), test(regex(uuid())))
        ])
        headers {
            messagingContentType('application/json')
        }
    }
}
