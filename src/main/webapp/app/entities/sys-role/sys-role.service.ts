import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysRole } from 'app/shared/model/sys-role.model';

type EntityResponseType = HttpResponse<ISysRole>;
type EntityArrayResponseType = HttpResponse<ISysRole[]>;

@Injectable({ providedIn: 'root' })
export class SysRoleService {
    public resourceUrl = SERVER_API_URL + 'api/sys-roles';

    constructor(protected http: HttpClient) {}

    create(sysRole: ISysRole): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysRole);
        return this.http
            .post<ISysRole>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysRole: ISysRole): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysRole);
        return this.http
            .put<ISysRole>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysRole>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysRole[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysRole: ISysRole): ISysRole {
        const copy: ISysRole = Object.assign({}, sysRole, {
            createTime: sysRole.createTime != null && sysRole.createTime.isValid() ? sysRole.createTime.toJSON() : null,
            updateTime: sysRole.updateTime != null && sysRole.updateTime.isValid() ? sysRole.updateTime.toJSON() : null
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
            res.body.forEach((sysRole: ISysRole) => {
                sysRole.createTime = sysRole.createTime != null ? moment(sysRole.createTime) : null;
                sysRole.updateTime = sysRole.updateTime != null ? moment(sysRole.updateTime) : null;
            });
        }
        return res;
    }
}
