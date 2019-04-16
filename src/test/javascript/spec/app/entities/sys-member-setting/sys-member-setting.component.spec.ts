/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysMemberSettingComponent } from 'app/entities/sys-member-setting/sys-member-setting.component';
import { SysMemberSettingService } from 'app/entities/sys-member-setting/sys-member-setting.service';
import { SysMemberSetting } from 'app/shared/model/sys-member-setting.model';

describe('Component Tests', () => {
    describe('SysMemberSetting Management Component', () => {
        let comp: SysMemberSettingComponent;
        let fixture: ComponentFixture<SysMemberSettingComponent>;
        let service: SysMemberSettingService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMemberSettingComponent],
                providers: []
            })
                .overrideTemplate(SysMemberSettingComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysMemberSettingComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysMemberSettingService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysMemberSetting(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysMemberSettings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
