import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysModule } from 'app/shared/model/sys-module.model';

type EntityResponseType = HttpResponse<ISysModule>;
type EntityArrayResponseType = HttpResponse<ISysModule[]>;

@Injectable({ providedIn: 'root' })
export class SysModuleService {
    public resourceUrl = SERVER_API_URL + 'api/sys-modules';

    constructor(protected http: HttpClient) {}

    create(sysModule: ISysModule): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysModule);
        return this.http
            .post<ISysModule>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysModule: ISysModule): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysModule);
        return this.http
            .put<ISysModule>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysModule>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysModule[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysModule: ISysModule): ISysModule {
        const copy: ISysModule = Object.assign({}, sysModule, {
            createTime: sysModule.createTime != null && sysModule.createTime.isValid() ? sysModule.createTime.toJSON() : null,
            updateTime: sysModule.updateTime != null && sysModule.updateTime.isValid() ? sysModule.updateTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysModule: ISysModule) => {
                sysModule.createTime = sysModule.createTime != null ? moment(sysModule.createTime) : null;
                sysModule.updateTime = sysModule.updateTime != null ? moment(sysModule.updateTime) : null;
            });
        }
        return res;
    }
}
