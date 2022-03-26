# Micronaut Data Project for reporting issues

Running the tests [HomeTest](src/test/kotlin/mn/data/HomeTest.kt) in this codebase will illustrate an issue with trying to set JsonFormat on a data class
tagged with an Entity, but does not have the same issue on a POJO version.

See [User and UserPOJO definitions](src/main/kotlin/mn/data/domain/User.kt)

The Entity version does not find a formatter to use, and simply does toString on the LocalDateTime,
and hence prints the time with 5 decimal places, instead of honouring the format.

I tried this with Instant too, same problem.

I think it's unable to find a formatter in com.fasterxml.jackson.datatype.jsr310.ser.JSR310FormattedSerializerBase.createContextual

