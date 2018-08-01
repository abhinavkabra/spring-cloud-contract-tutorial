org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url("/customers/${regex(uuid())}")
        headers {
            accept(applicationJsonUtf8())
        }
    }
    response {
        status 200
        body([
            "id": $(consumer('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'), producer(regex(uuid()))),
            "firstName": $(consumer("Shaun"), producer(regex('[a-zA-Z.-_]+'))),
            "lastName": $(consumer("Anderson"), producer(regex('[a-zA-Z.-_]+'))),
            "createdOn": $(consumer("2018-08-01T06:33:16+00:00"), producer(regex(iso8601WithOffset())))
        ])
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
