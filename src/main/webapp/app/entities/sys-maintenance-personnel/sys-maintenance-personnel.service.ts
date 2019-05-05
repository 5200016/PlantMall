import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';

type EntityResponseType = HttpResponse<ISysMaintenancePersonnel>;
type EntityArrayResponseType = HttpResponse<ISysMaintenancePersonnel[]>;

@Injectable({ providedIn: 'root' })
export class SysMaintenancePersonnelService {
    public resourceUrl = SERVER_API_URL + 'api/sys-maintenance-personnels';

    constructor(protected http: HttpClient) {}

    create(sysMaintenancePersonnel: ISysMaintenancePersonnel): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysMaintenancePersonnel);
        return this.http
            .post<ISysMaintenancePersonnel>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysMaintenancePersonnel: ISysMaintenancePersonnel): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysMaintenancePersonnel);
        return this.http
            .put<ISysMaintenancePersonnel>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysMaintenancePersonnel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysMaintenancePersonnel[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysMaintenancePersonnel: ISysMaintenancePersonnel): ISysMaintenancePersonnel {
        const copy: ISysMaintenancePersonnel = Object.assign({}, sysMaintenancePersonnel, {
            createTime:
                sysMaintenancePersonnel.createTime != null && sysMaintenancePersonnel.createTime.isValid()
                    ? sysMaintenancePersonnel.createTime.toJSON()
                    : null,
            updateTime:
                sysMaintenancePersonnel.updateTime != null && sysMaintenancePersonnel.updateTime.isValid()
                    ? sysMaintenancePersonnel.updateTime.toJSON()
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
            res.body.forEach((sysMaintenancePersonnel: ISysMaintenancePersonnel) => {
                sysMaintenancePersonnel.createTime =
                    sysMaintenancePersonnel.createTime != null ? moment(sysMaintenancePersonnel.createTime) : null;
                sysMaintenancePersonnel.updateTime =
                    sysMaintenancePersonnel.updateTime != null ? moment(sysMaintenancePersonnel.updateTime) : null;
            });
        }
        return res;
    }
}
