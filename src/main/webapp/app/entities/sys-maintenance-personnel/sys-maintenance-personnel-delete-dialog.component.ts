import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';
import { SysMaintenancePersonnelService } from './sys-maintenance-personnel.service';

@Component({
    selector: 'jhi-sys-maintenance-personnel-delete-dialog',
    templateUrl: './sys-maintenance-personnel-delete-dialog.component.html'
})
export class SysMaintenancePersonnelDeleteDialogComponent {
    sysMaintenancePersonnel: ISysMaintenancePersonnel;

    constructor(
        protected sysMaintenancePersonnelService: SysMaintenancePersonnelService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysMaintenancePersonnelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysMaintenancePersonnelListModification',
                content: 'Deleted an sysMaintenancePersonnel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-maintenance-personnel-delete-popup',
    template: ''
})
export class SysMaintenancePersonnelDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysMaintenancePersonnel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysMaintenancePersonnelDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysMaintenancePersonnel = sysMaintenancePersonnel;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
