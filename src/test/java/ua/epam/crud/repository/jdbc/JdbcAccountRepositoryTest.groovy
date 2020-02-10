package ua.epam.crud.repository.jdbc

import org.junit.Assert
import org.junit.Test
import ua.epam.crud.model.Account
import ua.epam.crud.model.AccountStatus
import ua.epam.crud.repository.AccountRepository

class JdbcAccountRepositoryTest {

    AccountRepository accountRepository = new JdbcAccountRepository()

    @Test
    void testGetById_ID_AND_STATUS_EQUALS() {
        Account expected = new Account(5L, AccountStatus.DELETED)
        accountRepository.create(expected)
        Account actual = accountRepository.getById(5L)
        Assert.assertEquals(expected.getIdDeveloper(), actual.getIdDeveloper())
        Assert.assertEquals(expected.getAccountStatus(), actual.getAccountStatus())
        accountRepository.delete(5L)
    }

    @Test
    void testCreate_ID_AND_STATUS_EQUALS() {
        Account expected = new Account(5L, AccountStatus.DELETED)
        accountRepository.create(expected)
        Account actual = accountRepository.getById(5L)
        Assert.assertEquals(expected.getIdDeveloper(), actual.getIdDeveloper())
        Assert.assertEquals(expected.getAccountStatus(), actual.getAccountStatus())
        accountRepository.delete(5L)
    }

    @Test
    void testDelete_ASSERT_NULL_AFTER_DELETE() {
        accountRepository.create(new Account(5L, AccountStatus.DELETED))
        accountRepository.delete(5L)
        Account actual = accountRepository.getById(5L)
        Assert.assertNull(actual)
    }


}
