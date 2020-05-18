package com.itheima.security.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBCrypt
{
    @Test
    public void testBCrypt()
    {
        //对密码进行加密
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());

        //校验密码
        boolean checkpw1 = BCrypt.checkpw("123", "$2a$10$/VgeNkua1JxAsqYs2irHFuGZ80Vdh7PIMOBSxTJqY0i4/7TQUDtci");
        boolean checkpw2 = BCrypt.checkpw("123","$2a$10$y/0S/xE5qBG.RHw4h1zyOORCpKIVfhKDQ.jENAdUFzQFJArInvqLS");
        System.out.println(checkpw1);
        System.out.println(checkpw2);

    }
}
