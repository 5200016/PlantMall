/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysClassifyComponent } from 'app/entities/sys-classify/sys-classify.component';
import { SysClassifyService } from 'app/entities/sys-classify/sys-classify.service';
import { SysClassify } from 'app/shared/model/sys-classify.model';

describe('Component Tests', () => {
    describe('SysClassify Management Component', () => {
        let comp: SysClassifyComponent;
        let fixture: ComponentFixture<SysClassifyComponent>;
        let service: SysClassifyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysClassifyComponent],
                providers: []
            })
                .overrideTemplate(SysClassifyComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysClassifyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysClassifyService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysClassify(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysClassifies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
