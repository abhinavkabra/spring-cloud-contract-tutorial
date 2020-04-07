package contracts;

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/customers'
        headers {
            accept(applicationJson())
        }
    }
    response {
        status 200
        body([
                [
                    "id": $(stub('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'), test(regex(uuid()))),
                    "firstName": $(stub("Matt"), test(regex('[a-zA-Z.-_]+'))),
                    "lastName": $(stub("Campbell"), test(regex('[a-zA-Z.-_]+'))),
                    "createdOn": $(stub("2018-08-01T05:33:16+00:00"), test(regex(iso8601WithOffset())))
                ],
                [
                    "id": $(stub('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'), test(regex(uuid()))),
                    "firstName": $(stub("Shaun"), test(regex('[a-zA-Z.-_]+'))),
                    "lastName": $(stub("Anderson"), test(regex('[a-zA-Z.-_]+'))),
                    "createdOn": $(stub("2018-08-01T06:33:16+00:00"), test(regex(iso8601WithOffset())))
                ]
        ])
        headers {
            contentType('application/json')
        }
    }
}
