ARCHITECTURE
------------

Project follows a clean approach and is structured in 3 modules (from outer to inner layers):

- app
- usecases
- data

Aditionally, there is a shared module:
- common

MODULE DESCRIPTION
------------------

"App" module (Android module) corresponds to the presentation layer. It is structured according to the Model-View-Presenter (MVP) pattern.

"Usecases" module (Java module) contains the business logic, splitted into definitions and implementations.

"Data" module (Java module) corresponds to the data layer. It is implemented with the repository pattern.

"Common" module (Java module) is referenced from the other modules. Contains shared elements used across the whole project.

DECOUPLING
----------

In order to decouple elements, Dagger2 is used as a injection mechanism.

Data is sent between modules using an event bus that posts the information that needs to be transmitted. Across the different layers, data is mapped
from API models to APP models using a hierachy of "XXXMapper" classes.

PERSISTENCE
-----------

Persistence is implemented in the presentation module using ROOM (Android Architecture Components).

Persistence during execution time is also achieved using a hierachy of "XXXHolder" classes (these are singleton objects that store data).

The rest API in the data module is implemented using Retrofit2.

Test contain both instrumentation and unit tests.

INCLUDED FUNCTIONALITIES
------------------------

- List/detail
- Data fetching from the API
- User activation/deactivation by clicking on the corresponding icon
- User search by name
- Data persistence

The "infinite data fetching" utility is implemented using a SwipeRefreshLayout.

Both sorting and exclusion of repeated data is implemented using the RecyclerView.SortedList class from the Android SDK. This data
stores the different criterias using a Comparator and communicates with the corresponding list adapter using a callback.

KNOWN BUGS
----------

Mockito is NOT injecting dependencies in the tests defined in the presentation layer. It's marked as a FIXME

FLAVORS
-------

The application defines "mock" and "prod" flavors.

The "mock" flavor uses a FakeDataSource that allows local testing instead of the real data source.

TECHNOLOGIES
------------

RxJava is used in the "data" module because reactive programming allows a better structure for data requests and error flows

Kotlin is used in the "data" and the "common" module to create data models (POJO'S). Functional programming may also come in handy
to sort and filter data collections
