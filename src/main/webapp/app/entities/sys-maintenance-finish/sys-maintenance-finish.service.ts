import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysMaintenanceFinish } from 'app/shared/model/sys-maintenance-finish.model';

type EntityResponseType = HttpResponse<ISysMaintenanceFinish>;
type EntityArrayResponseType = HttpResponse<ISysMaintenanceFinish[]>;

@Injectable({ providedIn: 'root' })
export class SysMaintenanceFinishService {
    public resourceUrl = SERVER_API_URL + 'api/sys-maintenance-finishes';

    constructor(protected http: HttpClient) {}

    create(sysMaintenanceFinish: ISysMaintenanceFinish): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysMaintenanceFinish);
        return this.http
            .post<ISysMaintenanceFinish>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysMaintenanceFinish: ISysMaintenanceFinish): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysMaintenanceFinish);
        return this.http
            .put<ISysMaintenanceFinish>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysMaintenanceFinish>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysMaintenanceFinish[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysMaintenanceFinish: ISysMaintenanceFinish): ISysMaintenanceFinish {
        const copy: ISysMaintenanceFinish = Object.assign({}, sysMaintenanceFinish, {
            createTime:
                sysMaintenanceFinish.createTime != null && sysMaintenanceFinish.createTime.isValid()
                    ? sysMaintenanceFinish.createTime.toJSON()
                    : null,
            updateTime:
                sysMaintenanceFinish.updateTime != null && sysMaintenanceFinish.updateTime.isValid()
                    ? sysMaintenanceFinish.updateTime.toJSON()
                    : null
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
            res.body.forEach((sysMaintenanceFinish: ISysMaintenanceFinish) => {
                sysMaintenanceFinish.createTime = sysMaintenanceFinish.createTime != null ? moment(sysMaintenanceFinish.createTime) : null;
                sysMaintenanceFinish.updateTime = sysMaintenanceFinish.updateTime != null ? moment(sysMaintenanceFinish.updateTime) : null;
            });
        }
        return res;
    }
}
