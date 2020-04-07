package contracts

org.springframework.cloud.contract.spec.Contract.make {
    label 'newCustomerAdded'
    input {
        triggeredBy('newCustomerAddedTriggered()')
    }
    outputMessage {
        sentTo('newCustomerAdded')
        body([
                customerId: $(consumer('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'), producer(regex(uuid())))
        ])
        headers {
            messagingContentType('application/json')
        }
    }
}
