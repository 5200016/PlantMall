import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysUser } from 'app/shared/model/sys-user.model';

type EntityResponseType = HttpResponse<ISysUser>;
type EntityArrayResponseType = HttpResponse<ISysUser[]>;

@Injectable({ providedIn: 'root' })
export class SysUserService {
    public resourceUrl = SERVER_API_URL + 'api/sys-users';

    constructor(protected http: HttpClient) {}

    create(sysUser: ISysUser): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysUser);
        return this.http
            .post<ISysUser>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysUser: ISysUser): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysUser);
        return this.http
            .put<ISysUser>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysUser>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysUser[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysUser: ISysUser): ISysUser {
        const copy: ISysUser = Object.assign({}, sysUser, {
            createTime: sysUser.createTime != null && sysUser.createTime.isValid() ? sysUser.createTime.toJSON() : null,
            updateTime: sysUser.updateTime != null && sysUser.updateTime.isValid() ? sysUser.updateTime.toJSON() : null
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
            res.body.forEach((sysUser: ISysUser) => {
                sysUser.createTime = sysUser.createTime != null ? moment(sysUser.createTime) : null;
                sysUser.updateTime = sysUser.updateTime != null ? moment(sysUser.updateTime) : null;
            });
        }
        return res;
    }
}
