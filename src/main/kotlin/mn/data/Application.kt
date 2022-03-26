package mn.data

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("mn.data")
		.banner(false)
		.start()
}

