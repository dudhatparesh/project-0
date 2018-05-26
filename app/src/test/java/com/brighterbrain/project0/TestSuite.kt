package com.brighterbrain.project0

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        MainPresenterTest::class,
        ViewItemsPresenterTest::class,
        SaveItemPresenterTest::class
)
class TestSuite