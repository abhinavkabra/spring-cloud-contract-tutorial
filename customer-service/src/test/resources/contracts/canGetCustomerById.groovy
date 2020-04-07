package contracts;

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url("/customers/${regex(uuid())}")
        headers {
            accept(applicationJson())
        }
    }
    response {
        status 200
        body([
            "id": $(stub('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'), test(regex(uuid()))),
            "firstName": $(stub("Shaun"), test(regex('[a-zA-Z.-_]+'))),
            "lastName": $(stub("Anderson"), test(regex('[a-zA-Z.-_]+'))),
            "createdOn": $(stub("2018-08-01T06:33:16+00:00"), test(regex(iso8601WithOffset())))
        ])
        headers {
            contentType('application/json')
        }
    }
}
