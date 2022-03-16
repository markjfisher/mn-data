Micronaut Data Project illustrating StackOverflow error trying to set back reference in a One to Many Relationship

There are 2 tests in this project, the first illustrates a behaviour I wasn't expecting, that when I add a child
to a parent, the child doesn't automatically get the reference back to the parent.

So the 2nd test is to try and create that link by adding it manually and saving the data.
However it causes a stackoverflow with 

```text
java.lang.StackOverflowError
	at java.base/java.util.AbstractSet.hashCode(AbstractSet.java:120)
	at org.hibernate.collection.internal.PersistentSet.hashCode(PersistentSet.java:459)
	at mn.data.domain.Artist.hashCode(ArtistRepository.kt)
	at mn.data.domain.Pack.hashCode(PackRepository.kt)
	at java.base/java.util.AbstractSet.hashCode(AbstractSet.java:124)
	at org.hibernate.collection.internal.PersistentSet.hashCode(PersistentSet.java:459)
	at mn.data.domain.Artist.hashCode(ArtistRepository.kt)
	at mn.data.domain.Pack.hashCode(PackRepository.kt)
	... etc
```

How do I write a correct OneToMany entity with back references and should it do it automatically?
