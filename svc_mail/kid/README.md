
# 广告邮件服务器

---

## 功能介绍

负责发送广告邮件，推广公司产品

---

## 目录结构

  - kid
    - bin
      - plugins
          - core
          - mail
      - pythons                         #邮件脚本 python
    - plugins
      - mail      
    - pub
    - Makefile
  - library

---

## 配置 ./bin/plugins/mail/mail_cofing.json

```json
{
	"mysql": [
		{
			"host" : "127.0.0.1",
			"port" : 3306,
			"user" : "root",
			"pass" : "dataservice2015",
			"name" : "crawler_robot"
		}
	],
    "MailSendAccounts": [
        {		
            "account": "",                  #用户发送邮件的账户
            "password": "",                 #用于发送邮件的密码
			"smtp_domain" : ""              #账户域 如： smtp.163.com
        }
        ,
        {
            "account": "",
            "password": "",
			"smtp_domain" : ""
        }
    ]
}

```
---

## 编译运行
  
配置好上面的配置文件放入 ./bin/plugins/mail下