package com.github.base.bean;

import java.util.List;

/**
 * Created by bingo on 2020/11/17.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/17
 */

public class IAMAuth {

    /**
     * auth : {"identity":{"methods":["password"],"password":{"user":{"domain":{"name":"IAMDomain"},"name":"IAMUser","password":"IAMPassword"}}},"scope":{"domain":{"name":"IAMDomain"}}}
     */

    private AuthBean auth;

    public AuthBean getAuth() {
        return auth;
    }

    public void setAuth(AuthBean auth) {
        this.auth = auth;
    }

    public static class AuthBean {
        /**
         * identity : {"methods":["password"],"password":{"user":{"domain":{"name":"IAMDomain"},"name":"IAMUser","password":"IAMPassword"}}}
         * scope : {"domain":{"name":"IAMDomain"}}
         */

        private IdentityBean identity;
        private ScopeBean scope;

        public IdentityBean getIdentity() {
            return identity;
        }

        public void setIdentity(IdentityBean identity) {
            this.identity = identity;
        }

        public ScopeBean getScope() {
            return scope;
        }

        public void setScope(ScopeBean scope) {
            this.scope = scope;
        }

        public static class IdentityBean {
            /**
             * methods : ["password"]
             * password : {"user":{"domain":{"name":"IAMDomain"},"name":"IAMUser","password":"IAMPassword"}}
             */

            private PasswordBean password;
            private List<String> methods;

            public PasswordBean getPassword() {
                return password;
            }

            public void setPassword(PasswordBean password) {
                this.password = password;
            }

            public List<String> getMethods() {
                return methods;
            }

            public void setMethods(List<String> methods) {
                this.methods = methods;
            }

            public static class PasswordBean {
                /**
                 * user : {"domain":{"name":"IAMDomain"},"name":"IAMUser","password":"IAMPassword"}
                 */

                private UserBean user;

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public static class UserBean {
                    /**
                     * domain : {"name":"IAMDomain"}
                     * name : IAMUser
                     * password : IAMPassword
                     */

                    private DomainBean domain;
                    private String name;
                    private String password;

                    public DomainBean getDomain() {
                        return domain;
                    }

                    public void setDomain(DomainBean domain) {
                        this.domain = domain;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getPassword() {
                        return password;
                    }

                    public void setPassword(String password) {
                        this.password = password;
                    }

                    public static class DomainBean {
                        /**
                         * name : IAMDomain
                         */

                        private String name;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }
        }

        public static class ScopeBean {
            /**
             * domain : {"name":"IAMDomain"}
             */

            private DomainBeanX domain;

            public DomainBeanX getDomain() {
                return domain;
            }

            public void setDomain(DomainBeanX domain) {
                this.domain = domain;
            }

            public static class DomainBeanX {
                /**
                 * name : IAMDomain
                 */

                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
