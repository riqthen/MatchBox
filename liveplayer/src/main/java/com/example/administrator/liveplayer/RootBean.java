package com.example.administrator.liveplayer;

import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */

public class RootBean {



    private int dm_error;
    private String error_msg;
    private int expire_time;
    private List<LivesBean> lives;

    public int getDm_error() {
        return dm_error;
    }

    public void setDm_error(int dm_error) {
        this.dm_error = dm_error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public int getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(int expire_time) {
        this.expire_time = expire_time;
    }

    public List<LivesBean> getLives() {
        return lives;
    }

    public void setLives(List<LivesBean> lives) {
        this.lives = lives;
    }

    public static class LivesBean {

        private String city;
        private CreatorBean creator;
        private String id;
        private String image;
        private String name;
        private int pub_stat;
        private int room_id;
        private String share_addr;
        private int slot;
        private int status;
        private String stream_addr;
        private int version;
        private int online_users;
        private int group;
        private int link;
        private int optimal;
        private int multi;
        private int rotate;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPub_stat() {
            return pub_stat;
        }

        public void setPub_stat(int pub_stat) {
            this.pub_stat = pub_stat;
        }

        public int getRoom_id() {
            return room_id;
        }

        public void setRoom_id(int room_id) {
            this.room_id = room_id;
        }

        public String getShare_addr() {
            return share_addr;
        }

        public void setShare_addr(String share_addr) {
            this.share_addr = share_addr;
        }

        public int getSlot() {
            return slot;
        }

        public void setSlot(int slot) {
            this.slot = slot;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStream_addr() {
            return stream_addr;
        }

        public void setStream_addr(String stream_addr) {
            this.stream_addr = stream_addr;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getOnline_users() {
            return online_users;
        }

        public void setOnline_users(int online_users) {
            this.online_users = online_users;
        }

        public int getGroup() {
            return group;
        }

        public void setGroup(int group) {
            this.group = group;
        }

        public int getLink() {
            return link;
        }

        public void setLink(int link) {
            this.link = link;
        }

        public int getOptimal() {
            return optimal;
        }

        public void setOptimal(int optimal) {
            this.optimal = optimal;
        }

        public int getMulti() {
            return multi;
        }

        public void setMulti(int multi) {
            this.multi = multi;
        }

        public int getRotate() {
            return rotate;
        }

        public void setRotate(int rotate) {
            this.rotate = rotate;
        }

        public static class CreatorBean {

            private String emotion;
            private int inke_verify;
            private int verified;
            private String description;
            private int gender;
            private String profession;
            private int sex;
            private String verified_reason;
            private String nick;
            private String location;
            private String birth;
            private String hometown;
            private int id;
            private String portrait;
            private int gmutex;
            private String third_platform;
            private int level;
            private int rank_veri;
            private String veri_info;

            public String getEmotion() {
                return emotion;
            }

            public void setEmotion(String emotion) {
                this.emotion = emotion;
            }

            public int getInke_verify() {
                return inke_verify;
            }

            public void setInke_verify(int inke_verify) {
                this.inke_verify = inke_verify;
            }

            public int getVerified() {
                return verified;
            }

            public void setVerified(int verified) {
                this.verified = verified;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getProfession() {
                return profession;
            }

            public void setProfession(String profession) {
                this.profession = profession;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getVerified_reason() {
                return verified_reason;
            }

            public void setVerified_reason(String verified_reason) {
                this.verified_reason = verified_reason;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            public String getHometown() {
                return hometown;
            }

            public void setHometown(String hometown) {
                this.hometown = hometown;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPortrait() {
                return portrait;
            }

            public void setPortrait(String portrait) {
                this.portrait = portrait;
            }

            public int getGmutex() {
                return gmutex;
            }

            public void setGmutex(int gmutex) {
                this.gmutex = gmutex;
            }

            public String getThird_platform() {
                return third_platform;
            }

            public void setThird_platform(String third_platform) {
                this.third_platform = third_platform;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getRank_veri() {
                return rank_veri;
            }

            public void setRank_veri(int rank_veri) {
                this.rank_veri = rank_veri;
            }

            public String getVeri_info() {
                return veri_info;
            }

            public void setVeri_info(String veri_info) {
                this.veri_info = veri_info;
            }
        }
    }
}
