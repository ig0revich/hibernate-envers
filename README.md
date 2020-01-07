## Spring Boot & Hibernate Envers Demo

### From [official documentation](https://hibernate.org/orm/envers/):

> The Envers module is a core Hibernate model that works both with Hibernate and JPA. In fact, you can use Envers anywhere Hibernate works whether that is standalone, inside WildFly or JBoss AS, Spring, Grails, etc.
> The Envers module aims to provide an easy auditing / versioning solution for entity classes.
>
> Features
> - Auditing of all mappings defined by the JPA specification
> - Auditing some Hibernate mappings that extend JPA, e.g. custom types and collections/maps of "simple" types like Strings, Integers.
> - Logging data for each revision using a "revision entity"
> - Querying historical snapshots of an entity and its associations

### Spring Boot test log fragment
```
...
Hibernate: 
    insert 
    into
        good_events
        (create_date_time, name, update_date_time, id) 
    values
        (?, ?, ?, ?)
Hibernate: 
    insert 
    into
        revinfo
        (rev, revtstmp) 
    values
        (null, ?)
Hibernate: 
    insert 
    into
        good_events_aud
        (revtype, name, id, rev) 
    values
        (?, ?, ?, ?)
...
```

"revinfo" and "good_events_aud" tables were created automatically.