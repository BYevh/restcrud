package ua.epam.crud.repository.jdbc

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.testng.Assert
import ua.epam.crud.model.Account
import ua.epam.crud.model.AccountStatus
import ua.epam.crud.repository.AccountRepository

class JdbcAccountRepositoryTest {

    AccountRepository accountRepository = new JdbcAccountRepository();

    @Test
    void testGetById_ID_AND_STATUS_EQUALS() {
        Account expected = new Account(1L, AccountStatus.ACTIVE);
        Account actual = accountRepository.getById(1L);
        Assert.assertEquals(expected.getIdDeveloper(), actual.getIdDeveloper());
        Assert.assertEquals(expected.getAccountStatus(), actual.getAccountStatus());
    }

    @Test
    void testCreate_ID_AND_STATUS_EQUALS() {
        Account expected = new Account(5L, AccountStatus.DELETED);
        accountRepository.create(expected);
        Account actual = accountRepository.getById(5L);
        Assert.assertEquals(expected.getIdDeveloper(), actual.getIdDeveloper());
        Assert.assertEquals(expected.getAccountStatus(), actual.getAccountStatus());
    }

    @Test
    void testDelete_ASSERT_NULL_AFTER_DELETE() {
        accountRepository.create(new Account(5L, AccountStatus.DELETED));
        accountRepository.delete(5L);
        Account actual = accountRepository.getById(5L);
        Assert.assertNull(actual);
    }


}
