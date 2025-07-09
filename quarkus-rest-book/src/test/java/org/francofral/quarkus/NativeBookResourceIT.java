package org.francofral.quarkus;

/**
 * @NativeImageTest is not available in quarkus 3.24.2. So it was removed
 *
 * You don't need to annotate your test class with @NativeImageTest anymore. Just reuse the same test class
 * used for JVM mode, or split the test classes by using Quarkus test profiles.
 *
 * ./mvnw verify -Dnative
 */
// @NativeImageTest
//public class NativeBookResourceIT extends BookResourceTest {
//}
