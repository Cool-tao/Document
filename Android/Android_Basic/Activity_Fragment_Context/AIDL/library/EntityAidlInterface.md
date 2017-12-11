#### EntityAidlInterface  

```
package com.alex.andfun.service.back.model;
import com.alex.andfun.service.back.entity.DownloadMessageEntity;

interface IDownloadEntityAidlInterface {
        DownloadMessageEntity getDownloadMessageEntity();
        void addMessage(inout DownloadMessageEntity entity);
}
```