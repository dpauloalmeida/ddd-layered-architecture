### Difference between Application Service and Domain Service
The best practice is to declare these as Domain Services. Domain Services are distinct from Application Services – unlike Application Services, they do contain business logic. The best example of a Domain Service in Bullsfirst is the MatchingEngine (in the Exchange) – it contains business logic to match orders and execute trades. This logic does not naturally belong inside the Order because we need to apply this logic across many orders.

A good Domain Service has three characteristics:

1. The operation relates to a domain concept that is not a natural part of an Entity or a Value Object.
2. The interface is defined in terms of other objects in the domain model
3. The operation is stateless.