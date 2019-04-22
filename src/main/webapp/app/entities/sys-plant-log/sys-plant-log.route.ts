import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysPlantLog } from 'app/shared/model/sys-plant-log.model';
import { SysPlantLogService } from './sys-plant-log.service';
import { SysPlantLogComponent } from './sys-plant-log.component';
import { SysPlantLogDetailComponent } from './sys-plant-log-detail.component';
import { SysPlantLogUpdateComponent } from './sys-plant-log-update.component';
import { SysPlantLogDeletePopupComponent } from './sys-plant-log-delete-dialog.component';
import { ISysPlantLog } from 'app/shared/model/sys-plant-log.model';

@Injectable({ providedIn: 'root' })
export class SysPlantLogResolve implements Resolve<ISysPlantLog> {
    constructor(private service: SysPlantLogService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysPlantLog> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysPlantLog>) => response.ok),
                map((sysPlantLog: HttpResponse<SysPlantLog>) => sysPlantLog.body)
            );
        }
        return of(new SysPlantLog());
    }
}

export const sysPlantLogRoute: Routes = [
    {
        path: 'sys-plant-log',
        component: SysPlantLogComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysPlantLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-plant-log/:id/view',
        component: SysPlantLogDetailComponent,
        resolve: {
            sysPlantLog: SysPlantLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysPlantLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-plant-log/new',
        component: SysPlantLogUpdateComponent,
        resolve: {
            sysPlantLog: SysPlantLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysPlantLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-plant-log/:id/edit',
        component: SysPlantLogUpdateComponent,
        resolve: {
            sysPlantLog: SysPlantLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysPlantLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysPlantLogPopupRoute: Routes = [
    {
        path: 'sys-plant-log/:id/delete',
        component: SysPlantLogDeletePopupComponent,
        resolve: {
            sysPlantLog: SysPlantLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysPlantLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
