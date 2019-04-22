import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysPlantLog } from 'app/shared/model/sys-plant-log.model';
import { SysPlantLogService } from './sys-plant-log.service';

@Component({
    selector: 'jhi-sys-plant-log-delete-dialog',
    templateUrl: './sys-plant-log-delete-dialog.component.html'
})
export class SysPlantLogDeleteDialogComponent {
    sysPlantLog: ISysPlantLog;

    constructor(
        protected sysPlantLogService: SysPlantLogService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysPlantLogService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysPlantLogListModification',
                content: 'Deleted an sysPlantLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-plant-log-delete-popup',
    template: ''
})
export class SysPlantLogDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysPlantLog }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysPlantLogDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysPlantLog = sysPlantLog;
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
